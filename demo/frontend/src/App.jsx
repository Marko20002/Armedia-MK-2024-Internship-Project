import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Layout from './components/Layout'
import Login from './pages/Login'
import PersonList from './pages/PersonList'
import PersonDetail from './pages/PersonDetail'
import PersonCreate from './pages/PersonCreate'
import CaseFileList from './pages/CaseFileList'
import CaseFileDetail from './pages/CaseFileDetail'
import CaseFileCreate from './pages/CaseFileCreate'

function PrivateRoute({ children }) {
  return localStorage.getItem('jwt') ? children : <Navigate to="/login" replace />
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route
          path="/"
          element={
            <PrivateRoute>
              <Layout />
            </PrivateRoute>
          }
        >
          <Route index element={<Navigate to="/persons" replace />} />
          <Route path="persons" element={<PersonList />} />
          <Route path="persons/new" element={<PersonCreate />} />
          <Route path="persons/:id" element={<PersonDetail />} />
          <Route path="cases" element={<CaseFileList />} />
          <Route path="cases/new" element={<CaseFileCreate />} />
          <Route path="cases/:id" element={<CaseFileDetail />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}
