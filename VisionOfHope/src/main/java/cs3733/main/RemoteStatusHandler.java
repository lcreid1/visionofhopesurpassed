package cs3733.main;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs3733.main.http.RemoteStatusRequest;
import cs3733.main.http.RemoteStatusResponse;

public class RemoteStatusHandler implements RequestHandler<RemoteStatusRequest,RemoteStatusResponse>{

	@Override
	public RemoteStatusResponse handleRequest(RemoteStatusRequest arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}

