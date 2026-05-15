import { useEffect, useState } from 'react'
import { useParams, Link } from 'react-router-dom'
import { api } from '../api'

const EMPTY_ADDRESS = { streetAddress: '', city: '', zip: '', country: '' }
const EMPTY_CONTACT = { type: 'EMAIL', value: '', description: '' }

function AddressForm({ personId, onSaved }) {
  const [form, setForm] = useState(EMPTY_ADDRESS)
  const [saving, setSaving] = useState(false)
  const [error, setError] = useState('')

  function set(field) {
    return e => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  async function submit(e) {
    e.preventDefault()
    setError('')
    setSaving(true)
    try {
      await api.persons.addAddress(personId, form)
      setForm(EMPTY_ADDRESS)
      onSaved()
    } catch (err) {
      setError(err.message)
    } finally {
      setSaving(false)
    }
  }

  return (
    <div className="inline-form">
      {error && <p className="error" style={{ marginBottom: '.75rem' }}>{error}</p>}
      <form className="form-grid" onSubmit={submit}>
        <div className="form-row">
          <label>
            Street Address *
            <input required value={form.streetAddress} onChange={set('streetAddress')} placeholder="123 Main St" />
          </label>
          <label>
            City *
            <input required value={form.city} onChange={set('city')} placeholder="City" />
          </label>
        </div>
        <div className="form-row">
          <label>
            ZIP *
            <input required value={form.zip} onChange={set('zip')} placeholder="10000" />
          </label>
          <label>
            Country *
            <input required value={form.country} onChange={set('country')} placeholder="Country" />
          </label>
        </div>
        <div className="form-actions">
          <button type="submit" className="btn-primary" disabled={saving}>
            {saving ? 'Saving…' : 'Save Address'}
          </button>
        </div>
      </form>
    </div>
  )
}

function ContactForm({ personId, onSaved }) {
  const [form, setForm] = useState(EMPTY_CONTACT)
  const [saving, setSaving] = useState(false)
  const [error, setError] = useState('')

  function set(field) {
    return e => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  async function submit(e) {
    e.preventDefault()
    setError('')
    setSaving(true)
    try {
      await api.persons.addContact(personId, form)
      setForm(EMPTY_CONTACT)
      onSaved()
    } catch (err) {
      setError(err.message)
    } finally {
      setSaving(false)
    }
  }

  return (
    <div className="inline-form">
      {error && <p className="error" style={{ marginBottom: '.75rem' }}>{error}</p>}
      <form className="form-grid" onSubmit={submit}>
        <div className="form-row">
          <label>
            Type *
            <select value={form.type} onChange={set('type')}>
              <option value="EMAIL">EMAIL</option>
              <option value="PHONE">PHONE</option>
            </select>
          </label>
          <label>
            Value *
            <input
              required
              value={form.value}
              onChange={set('value')}
              placeholder={form.type === 'EMAIL' ? 'email@example.com' : '+389 XX XXX XXX'}
            />
          </label>
        </div>
        <label>
          Description *
          <input required value={form.description} onChange={set('description')} placeholder="e.g. Work email" />
        </label>
        <div className="form-actions">
          <button type="submit" className="btn-primary" disabled={saving}>
            {saving ? 'Saving…' : 'Save Contact'}
          </button>
        </div>
      </form>
    </div>
  )
}

export default function PersonDetail() {
  const { id } = useParams()
  const [person, setPerson] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [showAddressForm, setShowAddressForm] = useState(false)
  const [showContactForm, setShowContactForm] = useState(false)

  function load() {
    return api.persons.details(id)
      .then(data => setPerson(data?.value ?? data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false))
  }

  useEffect(() => { load() }, [id])

  function afterSave() {
    setShowAddressForm(false)
    setShowContactForm(false)
    setLoading(true)
    load()
  }

  if (loading) return <p>Loading…</p>
  if (error) return <p className="error">{error}</p>
  if (!person) return <p className="empty-state">Person not found.</p>

  return (
    <div>
      <Link to="/persons" className="back-link">← All Persons</Link>

      <div className="page-header">
        <h2>{person.name} {person.familyName}</h2>
        <Link to={`/cases/new?personId=${person.id}`} className="btn-primary">+ New Case File</Link>
      </div>

      <div className="detail-card">
        <dl className="detail-list">
          <dt>Date of Birth</dt>
          <dd>{person.dateOfBirth ?? '—'}</dd>
          <dt>Place of Birth</dt>
          <dd>{person.placeOfBirth}</dd>
        </dl>
      </div>

      {/* ── Addresses ── */}
      <div className="section-header">
        <h3>Addresses</h3>
        <button
          className="btn-sm"
          onClick={() => { setShowAddressForm(v => !v); setShowContactForm(false) }}
        >
          {showAddressForm ? '✕ Cancel' : '+ Add Address'}
        </button>
      </div>

      {showAddressForm && (
        <AddressForm personId={id} onSaved={afterSave} />
      )}

      {person.addresses?.length ? (
        <table className="data-table">
          <thead>
            <tr><th>Street</th><th>City</th><th>ZIP</th><th>Country</th></tr>
          </thead>
          <tbody>
            {person.addresses.map((a, i) => (
              <tr key={a.id ?? i}>
                <td>{a.streetAddress}</td>
                <td>{a.city}</td>
                <td>{a.zip}</td>
                <td>{a.country}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : !showAddressForm && <p className="empty-state">No addresses.</p>}

      {/* ── Contact Methods ── */}
      <div className="section-header">
        <h3>Contact Methods</h3>
        <button
          className="btn-sm"
          onClick={() => { setShowContactForm(v => !v); setShowAddressForm(false) }}
        >
          {showContactForm ? '✕ Cancel' : '+ Add Contact'}
        </button>
      </div>

      {showContactForm && (
        <ContactForm personId={id} onSaved={afterSave} />
      )}

      {person.contactMethods?.length ? (
        <table className="data-table">
          <thead>
            <tr><th>Type</th><th>Value</th><th>Description</th></tr>
          </thead>
          <tbody>
            {person.contactMethods.map((c, i) => (
              <tr key={c.id ?? i}>
                <td><span className="badge">{c.type}</span></td>
                <td>{c.value}</td>
                <td>{c.description ?? '—'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : !showContactForm && <p className="empty-state">No contact methods.</p>}
    </div>
  )
}
