import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { api } from '../api'

export default function PersonList() {
  const [persons, setPersons] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    api.persons.list()
      .then(setPersons)
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <p>Loading…</p>
  if (error) return <p className="error">{error}</p>

  return (
    <div>
      <div className="page-header">
        <h2>Persons</h2>
        <Link to="/persons/new" className="btn-primary">+ New Person</Link>
      </div>
      {persons.length === 0 ? (
        <p className="empty-state">No persons found.</p>
      ) : (
        <table className="data-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Family Name</th>
              <th>Place of Birth</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {persons.map(p => (
              <tr key={p.id}>
                <td>{p.id}</td>
                <td>{p.name}</td>
                <td>{p.familyName}</td>
                <td>{p.placeOfBirth}</td>
                <td>
                  <Link to={`/persons/${p.id}`}>View →</Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}
