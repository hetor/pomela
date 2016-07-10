<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quartz Manager</title>
</head>
<body>
<#if isShutdown>
<h1 style="color:red">The Scheduler is shutdown!</h1>
<#else>
<a href="${rc.contextPath}/start">Start</a>
<a href="${rc.contextPath}/shutdown">Shutdown</a>
<a href="${rc.contextPath}/pauseAll">Pause All</a>
<a href="${rc.contextPath}/resumeAll">Resume All</a>
<a href="${rc.contextPath}/">Refresh</a>
</#if>
<#if jobs??>
<h3>Jobs</h3>
<table border="1">
    <tr>
        <th>Group</th>
        <th>Name</th>
        <th>Executing</th>
        <th>Operate</th>
    </tr>
    <#list jobs as job>
        <tr>
            <td>${job.group}</td>
            <td>${job.name}</td>
            <td>${executingJobs?seq_contains(job.key)?string("Yes", "No")}</td>
            <td>
                <a href="${rc.contextPath}/job/pause?name=${job.name}&group=${job.group}">Pause</a>
                <a href="${rc.contextPath}/job/resume?name=${job.name}&group=${job.group}">Resume</a>
            <#--<a href="${rc.contextPath}/job/delete?name=${job.name}&group=${job.group}">Delete</a> -->
                <a href="${rc.contextPath}/job/trigger?name=${job.name}&group=${job.group}">Trigger</a>
            <#--<a href="${rc.contextPath}/job/trigger/add?name=${job.name}&group=${job.group}">Add Trigger</a>-->
            </td>
        </tr>
    </#list>
</table>
<br>
</#if>
<#if triggers??>
<h3>Triggers</h3>
<table border="1">
    <tr>
        <th>Job Group</th>
        <th>Job Name</th>
        <th>Group</th>
        <th>Name</th>
        <th>Cron</th>
        <th>Start Time</th>
        <th>Previous Fire Time</th>
        <th>Next Fire Time</th>
        <th>End Time</th>
        <th>Priority</th>
        <th>Trigger State</th>
        <th>Executing</th>
        <th>Operate</th>
    </tr>
    <#list triggers as trigger>
        <tr>
            <td>${trigger.jobGroup}</td>
            <td>${trigger.jobName}</td>
            <td>${trigger.group}</td>
            <td>${trigger.name}</td>
            <td>${(trigger.cronExpression)!}</td>
            <td>${(trigger.startTime?datetime)!}</td>
            <td>${(trigger.previousFireTime?datetime)!}</td>
            <td>${(trigger.nextFireTime?datetime)!}</td>
            <td>${(trigger.endTime?datetime)!}</td>
            <td>${trigger.priority}</td>
            <td>${triggerStates[trigger_index]}</td>
            <td>${executingTriggers?seq_contains(trigger.key)?string("Yes", "No")}</td>
            <td>
                <a href="${rc.contextPath}/trigger/pause?name=${trigger.name}&group=${trigger.group}">Pause</a>
                <a href="${rc.contextPath}/trigger/resume?name=${trigger.name}&group=${trigger.group}">Resume</a>
            <#--<a href="${rc.contextPath}/trigger/update?name=${trigger.name}&group=${trigger.group}">Update</a>-->
            <#--<a href="${rc.contextPath}/trigger/unschedule?name=${trigger.name}&group=${trigger.group}">Unschedule</a>-->
            </td>
        </tr>
    </#list>
</table>
<br>
</#if>
</body>
</html>