package cs3733.main;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import cs3733.main.http.DeleteVideoSegmentRequest;
import cs3733.main.http.DeleteVideoSegmentResponse;

public class DeleteVideoSegmentHandler implements RequestHandler<DeleteVideoSegmentRequest,DeleteVideoSegmentResponse>{

	@Override
	public DeleteVideoSegmentResponse handleRequest(DeleteVideoSegmentRequest arg0, Context arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
