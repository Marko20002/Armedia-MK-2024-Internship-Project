import { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'
import { api } from '../api'

function fmt(date) {
  if (!date) return '—'
  return new Date(date).toLocaleDateString()
}

export default function CaseFileDetail() {
  const { id } = useParams()
  const [caseFile, setCaseFile] = useState(null)
  const [editing, setEditing] = useState(false)
  const [form, setForm] = useState({})
  const [loading, setLoading] = useState(true)
  const [saving, setSaving] = useState(false)
  const [error, setError] = useState('')

  useEffect(() => {
    api.cases.get(id)
      .then(c => {
        setCaseFile(c)
        setForm({
          caseNumber: c.caseNumber ?? '',
          title: c.title ?? '',
          incidentDate: c.incidentDate
            ? new Date(c.incidentDate).toISOString().split('T')[0]
            : '',
          personId: c.person?.id ?? '',
        })
      })
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }, [id])

  function setField(field) {
    return e => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  async function save(e) {
    e.preventDefault()
    setSaving(true)
    try {
      const updated = await api.cases.update(id, {
        caseNumber: form.caseNumber,
        title: form.title,
        incidentDate: form.incidentDate || null,
        personId: form.personId ? Number(form.personId) : null,
      })
      // update endpoint returns Optional<CaseFile> — unwrap if needed
      setCaseFile(updated?.value ?? updated)
      setEditing(false)
    } catch (err) {
      setError(err.message)
    } finally {
      setSaving(false)
    }
  }

  if (loading) return <p>Loading…</p>
  if (error && !caseFile) return <p className="error">{error}</p>

  return (
    <div>
      <Link to="/cases" className="back-link">← All Case Files</Link>

      {editing ? (
        <>
          <h2>Edit Case File</h2>
          <div className="form-card">
            {error && <p className="error" style={{ marginBottom: '1rem' }}>{error}</p>}
            <form className="form-grid" onSubmit={save}>
              <label>
                Case Number
                <input value={form.caseNumber} onChange={setField('caseNumber')} />
              </label>
              <label>
                Title
                <input value={form.title} onChange={setField('title')} />
              </label>
              <label>
                Incident Date
                <input type="date" value={form.incidentDate} onChange={setField('incidentDate')} />
              </label>
              <label>
                Person ID
                <input type="number" value={form.personId} onChange={setField('personId')} />
              </label>
              <div className="form-actions">
                <button type="submit" className="btn-primary" disabled={saving}>
                  {saving ? 'Saving…' : 'Save'}
                </button>
                <button type="button" onClick={() => { setEditing(false); setError('') }}>Cancel</button>
              </div>
            </form>
          </div>
        </>
      ) : (
        <>
          <div className="page-header">
            <h2>{caseFile.title || '(Untitled)'}</h2>
            <button className="btn-primary" onClick={() => setEditing(true)}>Edit</button>
          </div>
          <div className="detail-card">
            <dl className="detail-list">
              <dt>Case Number</dt>
              <dd>{caseFile.caseNumber ?? '—'}</dd>
              <dt>Incident Date</dt>
              <dd>{fmt(caseFile.incidentDate)}</dd>
            </dl>
          </div>

          {caseFile.person && (
            <>
              <h3>Person</h3>
              <div className="detail-card">
                <dl className="detail-list">
                  <dt>Name</dt>
                  <dd>
                    <Link to={`/persons/${caseFile.person.id}`}>
                      {caseFile.person.name} {caseFile.person.familyName}
                    </Link>
                  </dd>
                  <dt>Place of Birth</dt>
                  <dd>{caseFile.person.placeOfBirth ?? '—'}</dd>
                </dl>
              </div>
            </>
          )}
        </>
      )}
    </div>
  )
}
