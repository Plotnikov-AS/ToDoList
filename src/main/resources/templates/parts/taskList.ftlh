<#macro taskList>
    <#list tasks as task>
        <div>
            <b># ${task.id}</b>
            <i>${task.status}</i>
            <span>${task.tag}</span>
            <span>${task.description}
            <a href="/tasks/${task.id}">edit</a>
            </span>
        </div>
        <#else>
            No tasks
    </#list>
</#macro>