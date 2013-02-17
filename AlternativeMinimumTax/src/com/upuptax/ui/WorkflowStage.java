package com.upuptax.ui;

import javafx.scene.control.Button;

public class WorkflowStage {
	private Button button;
	private WorkflowStatus status=WorkflowStatus.NOTSTART;
	private int index;
	public WorkflowStage(Button botton,int index){
		this.button=button;
		this.index=index;
	}
	public WorkflowStatus getStatus() {
		return status;
	}
	public void setStatus(WorkflowStatus status) {
		this.status = status;
	}
	public Button getButton() {
		return button;
	}
	public int getIndex() {
		return index;
	}
	

}
