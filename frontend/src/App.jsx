import './App.css'
import EmployeeList from './pages/EmployeeList'

function App() {
  return (
    <div>
      <h1 className="text-3xl font-bold mb-4">Welcome to Employee Management</h1>
      <EmployeeList />  {/* <-- Render the Employee List component */}
    </div>
  )
}

export default App
