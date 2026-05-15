import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { api } from '../api'

export default function PersonCreate() {
  const nav = useNavigate()
  const [form, setForm] = useState({
    name: '',
    familyName: '',
    placeOfBirth: '',
    dateOfBirth: '',
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
        name: form.name,
        familyName: form.familyName,
        placeOfBirth: form.placeOfBirth,
        dateOfBirth: form.dateOfBirth || null,
      }
      const created = await api.persons.create(payload)
      nav(`/persons/${created.id}`)
    } catch (err) {
      setError(err.message)
    } finally {
      setSaving(false)
    }
  }

  return (
    <div>
      <Link to="/persons" className="back-link">← All Persons</Link>
      <h2>New Person</h2>
      <div className="form-card">
        {error && <p className="error" style={{ marginBottom: '1rem' }}>{error}</p>}
        <form className="form-grid" onSubmit={submit}>
          <label>
            Name *
            <input required value={form.name} onChange={set('name')} />
          </label>
          <label>
            Family Name *
            <input required value={form.familyName} onChange={set('familyName')} />
          </label>
          <label>
            Place of Birth *
            <input required value={form.placeOfBirth} onChange={set('placeOfBirth')} />
          </label>
          <label>
            Date of Birth
            <input type="date" value={form.dateOfBirth} onChange={set('dateOfBirth')} />
          </label>
          <div className="form-actions">
            <button type="submit" className="btn-primary" disabled={saving}>
              {saving ? 'Creating…' : 'Create Person'}
            </button>
            <button type="button" onClick={() => nav('/persons')}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  )
}
