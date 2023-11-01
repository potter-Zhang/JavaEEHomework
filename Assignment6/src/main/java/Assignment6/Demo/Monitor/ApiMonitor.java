package Assignment6.Demo.Monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class ApiMonitor {
    static HashMap<String, ApiData> datas = new HashMap<String, ApiData>();
    static Logger logger = LoggerFactory.getLogger(ApiMonitor.class);

    public static void showDatas() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n=== api statistics ===\n");
        stringBuilder.append("name\t\t\t\tcall\tmin_span\tmax_span\tavg_span\tnum_ex\n");
        for (Map.Entry<String, ApiData> entry : datas.entrySet()) {
            ApiData apiData = entry.getValue();
            String k = entry.getKey();
            stringBuilder.append(k.substring(k.lastIndexOf('.') + 1) + "\t\t" + apiData.getNum_call() + "\t\t" +
                    apiData.getMin_span() + "\t\t\t" + apiData.getMax_span() + "\t\t" +
                    apiData.getAvg_span() + "\t\t\t" + apiData.getNum_exceptions() + "\n");
        }
        logger.info(stringBuilder.toString());
    }

    @Pointcut("execution(* Assignment6.Demo.Api.UserService.*(..))")
    public void servicePointCut() {

    }

    @Around("servicePointCut()")
    public void updateData(ProceedingJoinPoint pjp) throws Throwable {
        Long start = Calendar.getInstance().getTimeInMillis();
        pjp.proceed();
        Long end = Calendar.getInstance().getTimeInMillis();
        ApiData apiData;
        if ((apiData = datas.get(pjp.getSignature().toString())) != null) {
            apiData.addData(end - start);
        } else {
            apiData = new ApiData();
            apiData.addData(end - start);
            datas.put(pjp.getSignature().toString(), apiData);
        }
    }

    @AfterThrowing("servicePointCut()")
    public void updateException(JoinPoint jp) {
        ApiData apiData;
        if ((apiData = datas.get(jp.getSignature().toString())) != null) {
            apiData.addExceptions();
        } else {
            apiData = new ApiData();
            apiData.addExceptions();
            datas.put(jp.getSignature().toString(), apiData);
        }
    }

}
