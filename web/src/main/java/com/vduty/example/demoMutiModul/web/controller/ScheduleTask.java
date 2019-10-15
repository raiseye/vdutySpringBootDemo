package com.vduty.example.demoMutiModul.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vduty.example.demoMutiModul.config.ConfigNormals;
import com.vduty.example.demoMutiModul.config.DynamicTimedTask;
import com.vduty.example.demoMutiModul.config.Globals;
import com.vduty.example.demoMutiModul.domain.TaskInfo;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

@RestController
@RequestMapping(value = "/task", method = RequestMethod.GET)
@Scope("prototype")
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ScheduleTask {

	@Autowired
	DynamicTimedTask dynamicTimedTask;

	@RequestMapping("/{title}")
	public String index(@PathVariable String title) {

		if (!StringUtils.isEmpty(title)) {

			boolean ru =  doTask(title);
			if (ru) {
				return "do task ok!";
			}
			else {
				return "new do new task!";
			}
			
		} else {
			return "task title is null!";
		}		
	}

	private boolean doTask(String title) {

		TaskInfo taskInfo = ConfigNormals.currentTasks.get(title);

		if (taskInfo != null)
			return false;
		else {
			taskInfo = new TaskInfo("");
			taskInfo.setCrateDate(new Date());
			ConfigNormals.currentTasks.put(title, taskInfo);
			dynamicTimedTask.setTaskTitle(title);
			
			Runnable run= new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("now do scheduletask title is " + title);
					//clear Globals
					Globals.taskMap.clear();
				}
			};
			
			dynamicTimedTask.startScheduleTask(run);
		}

		return true;
	}

}
