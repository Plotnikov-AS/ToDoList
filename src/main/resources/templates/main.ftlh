<#import "parts/common.ftlh" as c>
<#import "parts/taskList.ftlh" as tl>

<@c.page>
    <div>Список задач</div>
    <@tl.taskList>
    </@tl.taskList>
    <br><a href="/tasks/new">New task</a>
    <br><a href="/search">Search task</a>
</@c.page>