//関数
function formatDate(dt) {
    var y = dt.getFullYear();
    var m = ('00' + (dt.getMonth() + 1)).slice(-2);
    var d = ('00' + dt.getDate()).slice(-2);
    return (y + '-' + m + '-' + d);
}

function readTaskListAsJSON() {
    return JSON.parse(document.querySelector('#updated_task_list_by_gantt').value);
}

function overWrite(task) {
    let taskList = readTaskListAsJSON();
    taskList.forEach(function (value, index, array) {

        if (value.id == task.id) {
            taskList[index].start = task.start;
            taskList[index].end = task.end;
        }

    });

    document.querySelector('#updated_task_list_by_gantt').value = JSON.stringify(taskList);
}