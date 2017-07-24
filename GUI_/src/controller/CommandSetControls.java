package controller;

import java.util.List;

import view.View;

public class CommandSetControls implements Command {
		
		View v;
		private String upkeycode;
		private  String downkeycode;
		private String rightkeycode;
		private String leftkeycode; 
		
	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub
			v.setcontrols(upkeycode,downkeycode,leftkeycode,rightkeycode);
	}

	@Override
	public void setParams(List<Object> params) {
		// TODO Auto-generated method stub
        v=(View)params.get(0);
        upkeycode=params.get(1).toString();
        downkeycode=params.get(2).toString();
        leftkeycode=params.get(3).toString();
        rightkeycode=params.get(4).toString();
	}
}