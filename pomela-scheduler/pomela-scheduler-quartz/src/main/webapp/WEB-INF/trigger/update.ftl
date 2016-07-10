<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Trigger</title>
</head>
<body>
<form action="${rc.contextPath}/trigger/${triggerType}/update" method="post">
    <input type="hidden" name="origGroup" value="${trigger.group}" />
    <input type="hidden" name="origName" value="${trigger.name}" />
    <table>
        <tr><th align="right">Job Group: </th><td>${trigger.jobGroup}</td></tr>
        <tr><th align="right">Job Name: </th><td>${trigger.jobName}</td></tr>
        <tr><th align="right">Group: </th><td><input type="text" name="group" value="${trigger.group}" /></td></tr>
        <tr><th align="right">Name: </th><td><input type="text" name="name" value="${trigger.name}" /></td></tr>
        <tr><th align="right">Priority: </th><td><input type="text" name="priority" value="${trigger.priority}" /></td></tr>
        <tr><th align="right">Start Delay: </th><td><input type="text" name="startDelay" value="0" /></td></tr>
    <#switch triggerType>
        <#case "Simple">
            <tr><th align="right">Repeat Count: </th><td><input type="text" name="repeatCount" value="${trigger.repeatCount}" /></td></tr>
            <tr><th align="right">Repeat Interval: </th><td><input type="text" name="repeatInterval" value="${trigger.repeatInterval}" /></td></tr>
            <#break>
        <#case "Cron">
            <tr><th align="right">Cron Expression: </th><td><input type="text" name="cronExpression" value="${trigger.cronExpression}" /></td></tr>
            <#break>
    </#switch>
        <tr><th></th><td align="right"><input type="submit" value="Submit" /></td></tr>
    </table>
</form>
</body>
</html>