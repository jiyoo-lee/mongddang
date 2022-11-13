import { useState } from "react";
import {useNavigate, useLocation} from "react-router-dom"
import MyButton from "../button/MyButton";
import  PutAxios from "../../utils/axios/PutAxios";

const ChangePw = () => {

    const [password, setPassword] = useState("")
    const [checkPassword, setCheckPassword] = useState("")
    const navigate = useNavigate("");
    const location = useLocation();

    const testPassword = /^[A-Za-z0-9]{8,20}$/
    const userId = location.state.userId;

    const editPassword = () => {

        if(password.match(testPassword) === null){
            alert("패스워드 형식이 잘못되었습니다.")
        }
        else if(password !== checkPassword){
            alert("비밀번호가 일치하지 않습니다.")
        }
        else{
            PutAxios('/member/password',{userId:userId,password:password},(data)=>{console.log(data)}).then(alert("비밀번호가 변경되었습니다 "))
            navigate('/login')
        }
    }

    return (
        <div className="member_wrapper">
        <div className="member_form">
        <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}}/>
    <br/>
    <br/>
    <br/>
        <div> 
        <img src={process.env.PUBLIC_URL + `img/success.svg`}/>
    <br/>
    <br/>
        <span> <b>새 비밀번호를 설정해주세요</b> </span>
    <br/>
    <br/>    
        <input className="member_text" type='password' placeholder="영문+숫자 조합 8~16자리로 입력해주세요." value={password} onChange={(e)=>setPassword(e.target.value)}/>
    <br/>
        <input className="member_text" type='password' placeholder="비밀번호를 다시 한번 입력해주세요." value={checkPassword} onChange={(e)=>setCheckPassword(e.target.value)}/>
        </div> 
    <br/>
    <div className= 'login_btn_wrapper'>
        <MyButton text={'확인'} onClick={editPassword}/>
        </div>
     </div>
    </div>
    );
}


export default ChangePw;