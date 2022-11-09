import { useState } from "react";
import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";
import { PostAxios } from "../../utils/PostAxios";


const FindId = () => {
    const [emailValue, setEmailValue] = useState("")
    const navigate = useNavigate();

    let testEmail = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');

    const sendEmail = () => {
        if(emailValue.length < 5 || (!emailValue.includes("@") || !testEmail.test(emailValue))){
            alert("이메일을 다시 확인해주세요 ") 
        }
        else {
            PostAxios('/member/seeking-id/auth-number', {email : emailValue},
                      ()=>(navigate('/find-result')));
        }
    }

    return (
        <div className="member_wrapper">
            <div className="member_form">
            <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}} alt='login_logo'/>
            <br/>
            <br/>
            <br/>
                <input className="member_text" onChange={(e)=>setEmailValue(e.target.value)} type='text' placeholder="가입시 작성한 이메일을 입력해주세요."/>
            <br/>
            <br/>
            <br/>
            <div className='find_btn_wrapper'>
                <MyButton type={(emailValue.length > 5 && emailValue.includes('@') ? 'default' : 'nothing')} text={'인증코드 발송'}
                onClick={sendEmail}/>
            </div>
            
            </div>
        </div>

    );
}

export default FindId;