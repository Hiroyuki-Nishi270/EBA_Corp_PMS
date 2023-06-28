function createDependencies(_dependencyRow, _taskListShort) {
    console.log(_dependencyRow);
    if (_dependencyRow !== null && _dependencyRow !== undefined && _taskListShort !== null && _taskListShort !== undefined) {
        let dependencyRow = _dependencyRow.split(',');
        let taskListShort = JSON.parse(_taskListShort);

        //テーブルの表示
        let dependency_table = document.querySelector('#dependency_table');
        dependency_table.style.display = 'block';

        //console.log(dependencyRow);
        //テーブルデータの作成処理開始
        dependencyRow.forEach(element => {
            taskListShort.forEach(seeker => {
                if (seeker.id == element) {


                    let dependency_items = document.querySelector('#dependency_items');
                    dependency_items.children = null;

                    let dependency_item = document.createElement('tr');
                    dependency_item.setAttribute('id', 'dependency_item' + seeker.id);

                    let dependency_item_id = document.createElement('td');
                    let dependency_item_name = document.createElement('td');
                    let dependency_item_progress = document.createElement('td');
                    let dependency_item_start = document.createElement('td');
                    let dependency_item_end = document.createElement('td');
                    let dependency_item_button = document.createElement('td');

                    let dependency_item_link = document.createElement('a');
                    let dependency_item_button_b = document.createElement('button');

                    dependency_item_link.innerText = seeker.name;
                    dependency_item_link.setAttribute('href', '/ticket/' + seeker.id);

                    dependency_item_button_b.innerText = "削除";
                    dependency_item_button_b.setAttribute('type', 'button');
                    dependency_item_button_b.setAttribute('id', 'dependency_delete_button' + seeker.id);
                    dependency_item_button_b.setAttribute('onclick', 'remove(' + seeker.id + ')');
                    //dependency_item_button_b.addEventListener('click', remove())

                    dependency_item_id.innerText = seeker.id;
                    dependency_item_name.appendChild(dependency_item_link);
                    dependency_item_progress.innerText = seeker.progress;
                    dependency_item_start.innerText = seeker.start;
                    dependency_item_end.innerText = seeker.end;
                    dependency_item_button.appendChild(dependency_item_button_b);

                    dependency_item.appendChild(dependency_item_id);
                    dependency_item.appendChild(dependency_item_name);
                    dependency_item.appendChild(dependency_item_progress);
                    dependency_item.appendChild(dependency_item_start);
                    dependency_item.appendChild(dependency_item_end);
                    dependency_item.appendChild(dependency_item_button);

                    dependency_items.append(dependency_item);
                }
            })
        });
    }
}

function createDependencyData(dependencies) {
    if (dependencies !== null && dependencies !== undefined) {
        return dependencies.split(",");
    }
    return null;
}
function remove(x) {
    let task_dependencies = document.querySelector('#task_dependencies').value.split(',');

    console.log(x);
    console.log(task_dependencies);

    task_dependencies = task_dependencies.filter((t) => t != x);
    console.log(task_dependencies);

    document.querySelector('#task_dependencies').value = task_dependencies.join(',');

    document.querySelector('#dependency_items').removeChild(document.querySelector('#dependency_item' + x));

}

function add() { }
