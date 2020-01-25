<#import "parts/common.ftlh" as c>

<@c.page>
Task editor
<form action="/tasks" method=post>
    <div>
        Task id - ${task.id}<br>
        Task status - ${task.status}<br>
        Tag<br>
        <input type="text" name="tag" placeholder="tag" value=${task.tag}></input><br>
        Description<br>
        <input type="text" name="description" placeholder="Описание задачи" value=${task.description}></input><br>
        <button type="submit">Save</button>
        <input type="hidden" name="id" value=${task.id}></input>
    </div>
</form>
<a href="/main">Main page</a>
</@c.page>