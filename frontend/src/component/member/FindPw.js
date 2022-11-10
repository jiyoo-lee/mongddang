import { useState } from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/GetAxios";
import MyButton from "../button/MyButton";

const FindPw = () => {

    const navigate = useNavigate();
    const [memberId, setMemberId] = useState("");
    const [email, setEmail] = useState("");

    let testId = new RegExp(/^[a-z]+[a-z0-9]{4,19}$/g);
    let testEmail = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');

    const checkMember = () =>{
        if(memberId.length < 4 || !testId.test(memberId) || !testEmail.test(email)){
            alert(" 입력하신 정보를 다시 확인해주세요 ")
        }
        else {
            GetAxios('/member/update-password/auth-number',{params:{userId:memberId, email:email}}, (data)=>{
                navigate('/findPw-result',{state:{authNum:data.data,memberId:memberId}})
            })
        }
    }

    return (
        <div className="member_wrapper">
            <div className="member_form">
            <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}} alt='logo'/>

            <br/>
            <br/>
            <br/>
                <input className="member_text" onChange={(e)=>setMemberId(e.target.value)} type='text' placeholder="아이디를 입력해주세요."/>
            <br/>
                <input className="member_text" onChange={(e)=>setEmail(e.target.value)} type='text' placeholder="가입시 등록 이메일을 입력해주세요."/>
            <br/>
            <br/>
            <div className='find_btn_wrapper'>
                <MyButton text={'다음'} onClick={checkMember}/>
            </div>
            </div>
            
        </div>

    );
}

export default FindPw;