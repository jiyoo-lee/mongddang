import { useNavigate, useLocation } from "react-router-dom";
import { useState } from "react";
import MyButton from "../button/MyButton";


const FindPwResult = () => {

  const navigate = useNavigate(); 
  const location = useLocation();
  const [authNumber, setauthNumber] = useState("");

  const codeNumber = location.state.authNum;
  const memberId = location.state.memberId;
  
  const checkAuthNumber = () => {
    if(codeNumber === authNumber){
        navigate('/change-pw',{state:{userId:memberId}})
    }
    else{
        alert(" 인증코드를 다시 확인해주세요 ")
    }
  }

    return (
        <div className="member_wrapper">
        <div className="member_form">
        <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}}/>
        <br/>
        <br/>
        <br/>
        <span>해당 이메일로 인증 코드를 발송했습니다.</span> <br/> <br/>
        <input className="member_text" placeholder="인증코드 6자리를 입력해주세요." value={authNumber} onChange={(e)=>setauthNumber(e.target.value)}/>
        <div className='find_btn_wrapper'>
          <MyButton text={'인증코드 확인'} onClick={checkAuthNumber}/>
        </div>
        </div>
      </div>
    );
}

export default FindPwResult;