import { useState } from 'react';
import  axios  from 'axios';
import { useNavigate } from "react-router-dom";

import MyButton from '../button/MyButton'
import { PostAxios } from '../../utils/PostAxios';

const Login = ()=> {
    const [userId, setUserId] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const login = () => {
        if(userId.length < 5 || password < 5){
            alert("아이디와 패스워드를 다시 확인해주세요.")
        }
        else{
            const requestBody = {
                userId : userId,
                password : password
            };

            axios.get('https://geolocation-db.com/json/')
            .then((res) => {
                requestBody.accessIp = res.data["IPv4"];
                requestBody.latitude = res.data["latitude"];
                requestBody.longitude = res.data["longitude"]

                PostAxios('/member/login', requestBody, (data)=>{
                    sessionStorage.setItem("token", data.data);
                    alert("로그인 되었습니다.");
                    navigate('/home/feed');
                });
            })
        }
    }

    return (
        <>
    <div className="member_wrapper">
        <div className='member_form'>
        <img className='login_logo' alt="로그인" src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}}/> 
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