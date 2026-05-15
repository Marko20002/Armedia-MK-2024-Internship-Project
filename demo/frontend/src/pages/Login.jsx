import { useState } from 'react'
import { useNavigate, Navigate } from 'react-router-dom'
import { api } from '../api'

export default function Login() {
  const [form, setForm] = useState({ userName: '', password: '' })
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const nav = useNavigate()

  if (localStorage.getItem('jwt')) return <Navigate to="/persons" replace />

  function set(field) {
    return e => setForm(f => ({ ...f, [field]: e.target.value }))
  }

  async function submit(e) {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      const res = await api.login(form.userName, form.password)
      localStorage.setItem('jwt', res.jwt)
      nav('/persons')
    } catch {
      setError('Invalid username or password.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="auth-page">
      <form className="auth-form" onSubmit={submit}>
        <h1>CaseFile System</h1>
        {error && <p className="error">{error}</p>}
        <label>
          Username
          <input value={form.userName} onChange={set('userName')} autoComplete="username" required />
        </label>
        <label>
          Password
          <input type="password" value={form.password} onChange={set('password')} autoComplete="current-password" required />
        </label>
        <button type="submit" className="btn-primary" disabled={loading}>
          {loading ? 'Logging in…' : 'Log in'}
        </button>
      </form>
    </div>
  )
}
