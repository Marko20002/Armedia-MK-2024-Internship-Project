import { useState } from 'react'
import { useNavigate, useSearchParams, Link } from 'react-router-dom'
import { api } from '../api'

export default function CaseFileCreate() {
  const nav = useNavigate()
  const [searchParams] = useSearchParams()
  const [form, setForm] = useState({
    caseNumber: '',
    title: '',
    incidentDate: '',
    personId: searchParams.get('personId') ?? '',
  })
  const [error, setError] = useState('')
  const [saving, setSaving] = useState(false)

  function set(field) {
    return e => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  async function submit(e) {
    e.preventDefault()
    setError('')
    setSaving(true)
    try {
      const payload = {
        caseNumber: form.caseNumber,
        title: form.title,
        incidentDate: form.incidentDate || null,
        personId: form.personId ? Number(form.personId) : null,
      }
      const created = await api.cases.create(payload)
      const newId = created?.value?.id ?? created?.id
      nav(newId ? `/cases/${newId}` : '/cases')
    } catch (err) {
      setError(err.message)
    } finally {
      setSaving(false)
    }
  }

  return (
    <div>
      <Link to="/cases" className="back-link">← All Case Files</Link>
      <h2>New Case File</h2>
      <div className="form-card">
        {error && <p className="error" style={{ marginBottom: '1rem' }}>{error}</p>}
        <form className="form-grid" onSubmit={submit}>
          <label>
            Case Number
            <input value={form.caseNumber} onChange={set('caseNumber')} />
          </label>
          <label>
            Title *
            <input required value={form.title} onChange={set('title')} />
          </label>
          <label>
            Incident Date
            <input type="date" value={form.incidentDate} onChange={set('incidentDate')} />
          </label>
          <label>
            Person ID
            <input type="number" value={form.personId} onChange={set('personId')} placeholder="Leave blank if unknown" />
          </label>
          <div className="form-actions">
            <button type="submit" className="btn-primary" disabled={saving}>
              {saving ? 'Creating…' : 'Create Case File'}
            </button>
            <button type="button" onClick={() => nav('/cases')}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  )
}
