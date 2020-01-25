<#import "parts/common.ftlh" as c>
<#import "parts/taskList.ftlh" as tl>

<@c.page>
<a href="/main">Main page</a><br>
    <@tl.taskList>
    </@tl.taskList>
        <div>Найти задачу
            <form action="/search" method="post">
                <input type="text" name="id" placeholder="№ задачи">
                <button type="submit">Найти</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    </form>
        </div>
        <div>Или
            <form action="/search/searchByTag" method="post">
                <input type="text" name="tag" placeholder="Tag">
                <button type="submit">Найти</button>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    </form>
        </div>

</@c.page>