document.addEventListener('DOMContentLoaded', () => {
    // Handle logout
    document.getElementById('logoutBtn')?.addEventListener('click', () => {
        fetch('/logout', {
            method: 'POST',
            credentials: 'include'
        }).then(() => window.location.href = '/auth/login.html');
    });
    
    // Handle task form submission
    document.getElementById('addTaskBtn')?.addEventListener('click', addTask);
});

async function addTask() {
    const title = document.getElementById('taskTitle').value;
    const description = document.getElementById('taskDesc').value;
    const dueDate = document.getElementById('taskDueDate').value;
    
    try {
        const response = await fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title,
                description,
                dueDate: dueDate ? new Date(dueDate).toISOString() : null
            }),
            credentials: 'include'
        });
        
        if (!response.ok) {
            throw new Error('Failed to add task');
        }
        
        // Refresh task list
        loadTasks();
    } catch (error) {
        console.error('Error:', error);
    }
}
