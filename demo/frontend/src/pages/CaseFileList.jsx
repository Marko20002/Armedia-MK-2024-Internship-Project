import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { api } from '../api'

export default function CaseFileList() {
  const [cases, setCases] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    api.cases.list()
      .then(setCases)
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <p>Loading…</p>
  if (error) return <p className="error">{error}</p>

  return (
    <div>
      <div className="page-header">
        <h2>Case Files</h2>
        <Link to="/cases/new" className="btn-primary">+ New Case File</Link>
      </div>
      {cases.length === 0 ? (
        <p className="empty-state">No case files found.</p>
      ) : (
        <table className="data-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Case #</th>
              <th>Title</th>
              <th>Incident Date</th>
              <th>Person</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {cases.map(c => (
              <tr key={c.id}>
                <td>{c.id}</td>
                <td>{c.caseNumber ?? '—'}</td>
                <td>{c.title}</td>
                <td>{c.incidentDate ? new Date(c.incidentDate).toLocaleDateString() : '—'}</td>
                <td>
                  {c.person
                    ? <Link to={`/persons/${c.person.id}`}>{c.person.name} {c.person.familyName}</Link>
                    : '—'}
                </td>
                <td>
                  <Link to={`/cases/${c.id}`}>View →</Link>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}
