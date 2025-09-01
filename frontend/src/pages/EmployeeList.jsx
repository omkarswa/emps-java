import { useEffect, useState } from "react";
import { getEmployees, deleteEmployee, updateEmployee } from "../api/EmployeeApi";

export default function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const [editingEmployee, setEditingEmployee] = useState(null);

  useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = async () => {
    try {
      const res = await getEmployees();
      setEmployees(res.data);
    } catch (err) {
      console.error("Error fetching employees:", err);
    }
  };

  const handleDelete = async (id) => {
    await deleteEmployee(id);
    setEmployees(employees.filter((emp) => emp.id !== id));
  };

  const handleEditClick = (employee) => {
    setEditingEmployee({ ...employee });
  };

  const handleEditChange = (e) => {
    const { name, value } = e.target;
    setEditingEmployee((prev) => ({ ...prev, [name]: value }));
  };

  const handleEditSave = async () => {
    try {
      const res = await updateEmployee(editingEmployee.id, editingEmployee);
      setEmployees(
        employees.map((emp) =>
          emp.id === editingEmployee.id ? res.data : emp
        )
      );
      setEditingEmployee(null);
    } catch (err) {
      console.error("Error updating employee:", err);
    }
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-6 flex items-center gap-2">
        👨‍💼 Employee Directory
      </h2>

      {/* Table */}
      <div className="overflow-x-auto shadow-md rounded-lg">
        <table className="min-w-full border border-gray-200 bg-white">
          <thead className="bg-gray-100">
            <tr>
              <th className="px-4 py-2 text-left font-semibold">ID</th>
              <th className="px-4 py-2 text-left font-semibold">Name</th>
              <th className="px-4 py-2 text-left font-semibold">Dept</th>
              <th className="px-4 py-2 text-left font-semibold">Salary ($)</th>
              <th className="px-4 py-2 text-center font-semibold">Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((emp, idx) => (
              <tr
                key={emp.id}
                className={idx % 2 === 0 ? "bg-white" : "bg-gray-50"}
              >
                <td className="px-4 py-2">{emp.id}</td>
                <td className="px-4 py-2">{emp.name}</td>
                <td className="px-4 py-2">{emp.dept}</td>
                <td className="px-4 py-2">${emp.salary.toLocaleString()}</td>
                <td className="px-4 py-2 text-center space-x-2">
                  <button
                    onClick={() => handleEditClick(emp)}
                    className="px-3 py-1 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition"
                  >
                    ✏️ Edit
                  </button>
                  <button
                    onClick={() => handleDelete(emp.id)}
                    className="px-3 py-1 bg-red-500 text-white rounded-md hover:bg-red-600 transition"
                  >
                    🗑️ Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Edit Form */}
      {editingEmployee && (
        <div className="mt-6 p-4 border border-gray-300 rounded-lg bg-gray-50">
          <h3 className="text-lg font-semibold mb-3">Edit Employee</h3>
          <div className="space-y-3">
            <input
              type="text"
              name="name"
              value={editingEmployee.name}
              onChange={handleEditChange}
              placeholder="Name"
              className="w-full p-2 border rounded-md"
            />
            <input
              type="text"
              name="dept"
              value={editingEmployee.dept}
              onChange={handleEditChange}
              placeholder="Department"
              className="w-full p-2 border rounded-md"
            />
            <input
              type="number"
              name="salary"
              value={editingEmployee.salary}
              onChange={handleEditChange}
              placeholder="Salary"
              className="w-full p-2 border rounded-md"
            />
          </div>
          <div className="flex gap-3 mt-4">
            <button
              onClick={handleEditSave}
              className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 transition"
            >
              💾 Save
            </button>
            <button
              onClick={() => setEditingEmployee(null)}
              className="px-4 py-2 bg-gray-400 text-white rounded-md hover:bg-gray-500 transition"
            >
              ❌ Cancel
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
