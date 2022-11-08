import { useState } from 'react';

const Login = ()=> {

    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");

    const login = () => {
        fetch(`http://localhost:8080/api/v1/member/login`, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
                userId : userId,
                password : password,
                accessIp : '123.123.123.123',
                latitude : 0,
                longitude : 0
            })
        })
        .then(res=>res.json())
        .then(res=>{
            if (res.success) {
                alert("로그인 완료");
            }
        });
    }

    return (
    <div className="Login">
        <h2> 로그인 </h2>
        <div>
            id : <input type="text" value={userId} onChange={(e)=>setUserId(e.target.value)}/>
            <br/>
            pw : <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
        </div>
        
        <button onClick={login}> LOGIN </button>
    </div>
    );
}

export default Login;