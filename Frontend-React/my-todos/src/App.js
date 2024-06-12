import React, { useState, useEffect } from 'react';
import { AiOutlineDelete } from 'react-icons/ai';
import { BsCheckLg } from 'react-icons/bs';
import axios from 'axios';
import './App.css';

function App() {
  const [newTask, setNewTask] = useState('');
  const [description, setDescription] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [priority, setPriority] = useState('');
  const [tasks, setTasks] = useState([]);
  const [completed, setCompleted] = useState(false);

  // to save the todo's
  const handleAddTask = async (e) => {
    e.preventDefault();
    const newTaskData = {
      title: newTask,
      description: description,
      dueDate: dueDate,
      priority: priority
    };
    try {
      await axios.post('http://localhost:8080/api/v1/addTasks', newTaskData);
      fetchTasks();
      setNewTask('');
      setDescription('');
      setDueDate('');
      setPriority('');
    } catch (error) {
      console.error('Error adding task:', error);
    }
  };

  // to fetch the todo's details
  const fetchTasks = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/v1/getAllTasks');
      setTasks(response.data);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  // to delete the todo's
  const handleDeleteTask = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/deleteTask/${id}`);
      fetchTasks();
      alert('Your task is Deleted Successfully');
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  // to mark todo's as a complete
  const handleComplete = async (id) => {
    try {
      await axios.put(`http://localhost:8080/api/v1/updateTasks/${id}`, {
        completed: true,
      });
      fetchTasks(); 
      alert('your task is marked as completed');
    } catch (error) {
      console.error('Error completing task:', error);
      alert('Failed to mark task complete. Please try again.');
    }
  };

  // to delete the completed todo's
  const handleDeleteCompletedTask = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/deleteTask/${id}`);
      fetchTasks();
      alert('Your task is Deleted Successfully');
    } catch (error) {
      console.error('Error deleting completed task:', error);
    }
  };

  return (
    <div className="App">
      <h1>My-ToDo's</h1>
      <div className='form-todo'>
        <div className='todo-input'>
          <div className='todo-input-items'>
            <label>Title</label>
            <input type='text' value={newTask} onChange={(e) => setNewTask(e.target.value)} placeholder="What's the task title?" required />
          </div>
          <div className='todo-input-items'>
            <label>Description</label>
            <input type='text' value={description} onChange={(e) => setDescription(e.target.value)} placeholder="Enter the Task Description" required />
          </div>
          <div className='todo-input-items'>
            <label>Due Date</label>
            <input type='date' value={dueDate} onChange={(e) => setDueDate(e.target.value)} />
          </div>
          <div className='todo-input-items'>
            <label>Priority</label>
            <select value={priority} onChange={(e) => setPriority(e.target.value)}>
              <option value="">Select Priority</option>
              <option value="HIGH">High</option>
              <option value="MEDIUM">Medium</option>
              <option value="LOW">Low</option>
            </select>
          </div>
          <div className='todo-input-items'>
            <button type='button' onClick={handleAddTask} className='primaryBtn'>Add Task</button>
          </div>
        </div>

        <div className='btn-area'>
          <button className={`secondaryBtn ${completed === false && 'active'}`} onClick={() => setCompleted(false)}>ToDo</button>
          <button className={`secondaryBtn ${completed === true && 'active'}`} onClick={() => setCompleted(true)}>Completed</button>
        </div>

        <div className='todo-list'>
  {completed === false && tasks.map((item, index) => (
    !item.completed && (
      <div className='todo-list-item' key={index}>
        <div>
          <h2>{item.title}</h2>
          <p>{item.description}</p>
          <p>{item.dueDate}</p>
          <p>{item.priority}</p>
        </div>
        <div>
          <AiOutlineDelete className='icon' onClick={() => handleDeleteTask(item.id)} title='Delete?' />
          <BsCheckLg className='check-icon' onClick={() => handleComplete(item.id)} title='completed?' />
        </div>
      </div>
    )
  ))}
{completed === true && tasks.filter(task => task.completed).map((item, index) => (
  <div className='todo-list-item' key={index}>
    <div>
      <h2>{item.title}</h2>
      <p>{item.description}</p>
      <p>{item.dueDate}</p>
      <p>{item.priority}</p>
    </div>
    <div>
      <AiOutlineDelete className='icon' onClick={() => handleDeleteCompletedTask(item.id)} title='Delete?' />
    </div>
  </div>
))}
      </div>
    </div>
    </div>
  );
}

export default App;
