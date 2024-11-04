document.addEventListener('DOMContentLoaded', () => {
    const todoList = document.getElementById('todoList');
    const todoForm = document.getElementById('todoForm');
    const taskInput = document.getElementById('taskInput');

    // Fetch existing tasks from the server
    function fetchTasks() {
        fetch('/api/todos')
            .then(response => response.json())
            .then(tasks => {
                todoList.innerHTML = '';
                tasks.forEach(task => {
                    const li = document.createElement('li');
                    li.textContent = task.task;

                    const checkbox = document.createElement('input');
                    checkbox.type = 'checkbox';
                    checkbox.checked = task.completed;
                    checkbox.addEventListener('change', () => updateTaskStatus(task.id, checkbox.checked));

                    const deleteButton = document.createElement('button');
                    deleteButton.textContent = 'Delete';
                    deleteButton.addEventListener('click', () => deleteTask(task.id));

                    li.prepend(checkbox);
                    li.appendChild(deleteButton);
                    todoList.appendChild(li);
                });
            });
    }

    // Add a new task to the server
    todoForm.addEventListener('submit', event => {
        event.preventDefault();
        const task = taskInput.value.trim();
        if (task) {
            fetch('/api/todos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ task: task, completed: false }),
            })
            .then(response => {
                if (response.ok) {
                    taskInput.value = '';
                    fetchTasks();
                }
            });
        }
    });

    // Update task status
    function updateTaskStatus(id, completed) {
        fetch(`/api/todos/${id}?completed=${completed}`, {
            method: 'PATCH',
        })
        .then(response => {
            if (response.ok) {
                fetchTasks();
            }
        });
    }

    // Delete a task from the server
    function deleteTask(id) {
        fetch(`/api/todos/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                fetchTasks();
            }
        });
    }

    // Initial fetch to load tasks
    fetchTasks();
});
