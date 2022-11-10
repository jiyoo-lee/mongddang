import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";

const Join = () => {

    const navigate = useNavigate();

    return (
        <div className="member_wrapper">
        <div className='member_form'>
        <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}} alt='logo'/>
        <br/>
            <input className="member_text" type='text' placeholder="아이디를 입력해주세요."/>
        <br/>
            <input className="member_text" type='text' placeholder="비밀번호를 입력해주세요."/>
        <br/>
            <input className="member_text" type='text' placeholder="비밀번호를 다시 입력해주세요."/>
        <br/>
            <input className="member_text" type='text' placeholder="닉네임을 입력해주세요."/>
        <br/>
            <input className="member_text" type='text' placeholder="이메일을 입력해주세요."/>
        <br/>
            <input className="member_text" type='text' placeholder="전화번호를 입력해주세요."/>
        <div className='find_btn_wrapper'>
                <MyButton text={'가입하기'}/>
            </div>
        </div>
        </div>
    );
}

export default Join;