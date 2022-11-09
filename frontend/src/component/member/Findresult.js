import { useNavigate } from "react-router-dom";

const Findresult = () => {

  const navigate = useNavigate(); 

    return (
      <div className="member_wrapper">
        <div className="member_form">
        <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}}/>
        <br/>
        <br/>
        <br/>
        <span>해당 이메일로 인증 코드를 발송했습니다.</span> <br/> <br/>
        <input className="member_text" placeholder="인증코드 6자리를 입력해주세요."/>
        </div>
      </div>
    );
}

export default Findresult;