import axios from "axios";
import { useState } from "react";

function Login() {
  const [status, setStatus] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const signup = async () => {
    const res = await axios.post("http://localhost:8080/signup", { username, password }, { withCredentials: true });
    setStatus(res.data);
  };

  const login = async () => {
    const res = await axios.post("http://localhost:8080/login", { username, password }, { withCredentials: true });
    setStatus(res.data);
  };

  const check = async () => {
    const res = await axios.get("http://localhost:8080/check", { withCredentials: true });
    setStatus(res.data);
  };

  const logout = async () => {
    const res = await axios.post("http://localhost:8080/logout", null, { withCredentials: true });
    setStatus(res.data);
  };

  return (
   
  <div className="d-flex container bg-light justify-content-center align-items-center text-center" style={{ height: '100vh' }}>
    <div className="container shadow p-5 m-5">

      <div className="p-2 row">
        <label htmlFor="name" className="form-label col">Name : </label>
        <input placeholder="Username" onChange={e => setUsername(e.target.value)} className="form-control col" id="name" name="name" />
      </div>

      <div className="p-2 row">
        <label htmlFor="password" className="form-label col">Password : </label>
        <input
          placeholder="Password"
          type="password"
          onChange={e => setPassword(e.target.value)}
          className="form-control col"
          id="password"
          name="password"
        />
      </div>

      <div className="row d-flex justify-content-center">
        <button onClick={signup} className="m-2 btn btn-info rounded-pill col-2">Signup</button>
        <button onClick={login} className="m-2 btn btn-primary rounded-pill col-2">Login</button>
        <button onClick={check} className="m-2 btn btn-outline-success rounded-pill col-2">Check Session</button>
        <button onClick={logout} className="m-2 btn btn-outline-danger rounded-pill col-2">Logout</button>
      </div>

      <p className="fs-6 text-success text-center border border-dark rounded-pill">Status : {status}</p>

    </div>
  </div>
);

  
}

export default Login;
