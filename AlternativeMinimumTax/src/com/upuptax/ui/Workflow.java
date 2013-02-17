package com.upuptax.ui;

import java.util.LinkedList;

public class Workflow {
	private LinkedList<WorkflowStage> stages=new LinkedList<WorkflowStage>();
	private WorkflowStage currentStage;
	
	public void add(WorkflowStage stage){
		stages.add(stage);
	}

	public WorkflowStage getFirst(){
		return stages.getFirst();
	}
	public WorkflowStage getLast(){
		return stages.getLast();
	}
	public WorkflowStage getNext(){
		int ind = currentStage.getIndex()+1;
		if (ind>=stages.size())
			return null;
		else
			return stages.get(ind);
	}
	public WorkflowStage getPrevious(){
		int ind = currentStage.getIndex()-1;
		if (ind<0)
			return null;
		else
			return stages.get(ind);
	}

}
