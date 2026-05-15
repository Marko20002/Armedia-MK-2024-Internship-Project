function authHeaders() {
  const token = localStorage.getItem('jwt')
  return {
    'Content-Type': 'application/json',
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
  }
}

async function request(method, path, body) {
  const res = await fetch(path, {
    method,
    headers: authHeaders(),
    body: body !== undefined ? JSON.stringify(body) : undefined,
  })
  if (res.status === 401) {
    localStorage.removeItem('jwt')
    window.location.href = '/login'
    return
  }
  if (!res.ok) {
    const text = await res.text()
    throw new Error(text || `HTTP ${res.status}`)
  }
  // 204 No Content
  const ct = res.headers.get('content-type') ?? ''
  if (!ct.includes('application/json')) return null
  return res.json()
}

export const api = {
  login: (userName, password) =>
    request('POST', '/person/register', { userName, password }),

  persons: {
    list: ()        => request('GET',  '/person/listAll'),
    get: (id)       => request('GET',  `/person/${id}`),
    details: (id)   => request('GET',  `/person/getPersonDetails/${id}`),
    create: (data)  => request('POST', '/person/create', data),
    addAddress: (id, addr)    => request('POST', `/person/addAddress/${id}`, addr),
    addContact:  (id, contact) => request('POST', `/person/addContactMetod/${id}`, contact),
  },

  cases: {
    list: ()           => request('GET',  '/api/caseFiles/listAll'),
    get: (id)          => request('GET',  `/api/caseFiles/${id}`),
    create: (data)     => request('POST', '/api/caseFiles/create', data),
    update: (id, data) => request('PUT',  `/api/caseFiles/${id}/update`, data),
  },
}
