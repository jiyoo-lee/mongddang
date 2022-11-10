import { useNavigate, useLocation } from "react-router-dom";
import MyButton from "../button/MyButton";


const Find = () =>{
    
    const navigate = useNavigate("");
    const location = useLocation();
    
    const id = location.state.memberId;

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
            <span> 아이디를 확인하세요 </span>
        <br/>    
            <span className="info_id">{id}</span>    
            </div> 
        <br/>
        <div className= 'login_btn_wrapper'>
            <MyButton text={'로그인'} onClick={()=>{navigate('/login')}}/>
            <br/>
            <br/>
            <span> 또는 </span>
            <br/>
            <br/>
            <MyButton text={'비밀번호 찾기'} type={'negative'} onClick={()=>{navigate('/findPw')}}/>
            </div>
         </div>
        </div>
    );
}

export default Find;