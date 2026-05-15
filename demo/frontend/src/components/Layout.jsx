import { Outlet, NavLink, useNavigate } from 'react-router-dom'

export default function Layout() {
  const nav = useNavigate()

  function logout() {
    localStorage.removeItem('jwt')
    nav('/login')
  }

  return (
    <div className="layout">
      <nav className="sidebar">
        <div className="sidebar-title">CaseFile</div>
        <NavLink to="/persons">Persons</NavLink>
        <NavLink to="/cases">Case Files</NavLink>
        <button className="logout-btn" onClick={logout}>Logout</button>
      </nav>
      <main className="content">
        <Outlet />
      </main>
    </div>
  )
}
