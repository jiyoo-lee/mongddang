import { useState } from 'react';
import  axios  from 'axios';
import { useNavigate } from "react-router-dom";

import MyButton from '../button/MyButton'

const Login = ()=> {
    
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");
    const [ip, setIp] = useState("");
    const [latitude, setLatitude] = useState("");
    const [longitude, setLongitude] = useState("");
    const navigate = useNavigate();

    const login = () => {

        if(userId.length < 5 || password < 5){
            alert("아이디와 패스워드를 다시 확인해주세요.")
        }else{

        const res = axios.get('https://geolocation-db.com/json/')
        .then((res) => {
          setIp(res.data["IPv4"])
          setLatitude(res.data["latitude"])
          setLongitude(res.data["longitude"])
        })

        fetch(`http://localhost:8080/api/v1/member/login`, {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
                userId : userId,
                password : password,
                accessIp : ip,
                latitude : latitude,
                longitude : longitude
            })
        }) 
        .then(res=>res.json())
        .then(res=>{
            if (res.success) {
                alert("로그인 되었습니다.");
                navigate('/home/feed')
            }
        });
        }
    }

    return (
        <>
    <div className="member_wrapper">
        <div className='member_form'>
        <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}}/> 
            <br/>
            <br/>
            <br/>
            <input type="text" className='member_text' placeholder='아이디를 입력하세요'
             value={userId} onChange={(e)=>setUserId(e.target.value)}/>
            <br/>
            <input type="password" className='member_text' placeholder='비밀번호를 입력하세요'
            value={password} onChange={(e)=>setPassword(e.target.value)}/>
        <br/>
        <br/>
        <br/>
        <div className='login_btn_wrapper'>
            <MyButton text={'로그인'} onClick={login}/> 
            <br/>
            <br/>
            <span> 또는 </span>
            <br/>
            <br/>
            <MyButton text={'Google 로그인'} type={'negative'} onClick={login}/> 
            <br/> 
            <br/>
            <span className='find_info' onClick={()=> {navigate('/findId')}}>아이디 찾기  </span> |
            <span className='find_info' onClick={()=> {navigate('/findPw')}}>  비밀번호 찾기</span>
         </div>
        </div>
        </div>
    </>
    );
}

export default Login;