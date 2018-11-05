package com.example.common.base;

import com.example.common.dto.CuleResult;
import com.example.common.exception.ServiceProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    protected  final Logger logger=LoggerFactory.getLogger(this.getClass());
    protected CuleResult restProcessor(ResultProcessor processor){
        CuleResult result=null;
        try {
            result =processor.process();
        }catch (ServiceProcessException e1){
            logger.error("ServiceProcess Error Log :"+e1.getLocalizedMessage(),e1);
            result =CuleResult.error(e1.getMessage());
        }
        catch (Exception e){
            logger.error("Error Log:"+e.getLocalizedMessage(),e);
            result=CuleResult.error("服务器出现异常");
        }
        return  result;
    }
}
