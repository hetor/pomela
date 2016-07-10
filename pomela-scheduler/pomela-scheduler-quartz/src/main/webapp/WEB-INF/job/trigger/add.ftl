<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Trigger</title>
</head>
<body>
<form action="${rc.contextPath}/job/trigger/add" method="post">
    <input type="hidden" name="jobGroup" value="${jobKey.group}" />
    <input type="hidden" name="jobName" value="${jobKey.name}" />
    <table>
        <tr><th align="right">Job Group: </th><td>${jobKey.group}</td></tr>
        <tr><th align="right">Job Name: </th><td>${jobKey.name}</td></tr>
        <tr><th align="right">Group: </th><td><input type="text" name="group" required /></td></tr>
        <tr><th align="right">Name: </th><td><input type="text" name="name" required /></td></tr>
        <tr><th align="right">Priority: </th><td><input type="text" name="priority" value="5" /></td></tr>
        <tr><th align="right">Start Delay: </th><td><input type="text" name="startDelay" value="0" /></td></tr>
        <tr><th align="right">Trigger Type: </th><td><#list triggerTypeList as triggerType><label><input type="radio" name="type" value="${triggerType}" <#if triggerType_index=0>checked</#if> /> ${triggerType}</label></#list></td></tr>
        <tr><th align="right">Repeat Count: </th><td><input type="text" name="repeatCount" value="-1" /></td></tr>
        <tr><th align="right">Repeat Interval: </th><td><input type="text" name="repeatInterval" value="1000" /></td></tr>
        <tr><th align="right">Cron Expression: </th><td><input type="text" name="cronExpression" /></td></tr>
        <tr><th></th><td align="right"><input type="submit" value="Submit" /></td></tr>
    </table>
</form>
</body>
</html>