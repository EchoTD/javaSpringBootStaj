document.addEventListener("DOMContentLoaded", function () {
    loadTasks();

    // Handle task creation
    document.getElementById("task-form").addEventListener("submit", function (event) {
        event.preventDefault();
        const title = document.getElementById("task-title").value;
        const description = document.getElementById("task-desc").value;
        const dueDate = document.getElementById("dueDate").value;

        fetch("/tasks", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ title, description })
        })
        .then(response => response.json())
        .then(() => {
            loadTasks();  // Reload tasks after adding
            document.getElementById("task-title").value = "";
            document.getElementById("task-desc").value = "";
        })
        .catch(error => console.error("Error adding task:", error));
    });
});

// Function to load tasks
function loadTasks() {
    fetch("/tasks")
        .then(response => response.json())
        .then(tasks => {
            const list = document.getElementById("task-list");
            list.innerHTML = "";
            tasks.forEach(task => {
                const li = document.createElement("li");
                li.textContent = `${task.title} - ${task.description} - ${task.dueDate} -- ${task.status}`;

                // Delete button
                const deleteButton = document.createElement("button");
                deleteButton.textContent = "Delete";
                deleteButton.onclick = function () {
                    deleteTask(task.id);
                };

                li.appendChild(deleteButton);
                list.appendChild(li);
            });
        })
        .catch(error => console.error("Error fetching tasks:", error));
}

// Function to delete task
function deleteTask(id) {
    fetch(`/tasks/${id}`, { method: "DELETE" })
        .then(() => loadTasks()) // Reload tasks after deletion
        .catch(error => console.error("Error deleting task:", error));
}
