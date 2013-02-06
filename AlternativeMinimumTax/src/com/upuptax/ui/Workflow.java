package com.upuptax.ui;

import java.util.LinkedList;

public class Workflow {
	private LinkedList<WorkflowStage> stages;
	private WorkflowStage currentStage;
	public WorkflowStage getFirst(){
		return stages.getFirst();
	}
	public WorkflowStage getLast(){
		return stages.getLast();
	}

}
