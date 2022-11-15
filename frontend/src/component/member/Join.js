import { useNavigate } from "react-router-dom";
import MyButton from "../button/MyButton";
import { useState } from "react";
import  axios  from 'axios';
import GetAxios from "../../utils/axios/GetAxios";
import PostAxios  from "../../utils/axios/PostAxios";

const Join = () => {

    const navigate = useNavigate();
    
    const [inputs, setInputs] = useState({
        memberId : '',
        password: '',
        passwordcheck : '',
        name: '',
        nickname: '',
        email: '',
        phoneNumber: '',
    });
    
    const {memberId, password, passwordcheck, name, nickname, email, phoneNumber} = inputs
    const onChange = (e) => {
        const {name, value} = e.target;
        setInputs({
            ...inputs,
            [name] : value
        });
    };

    const [memberIdMessage, setMemberIdMessage] = useState("");
    const [nameMessage, setNameMessage] = useState("");
    const [passwordMessage, setPasswordMessage] = useState("");
    const [passwordCheckMessage, setPasswordCheckMessage] = useState("");
    const [nicknameMessage, setNicknameMessage] = useState("");
    const [emailMessage, setEmailMessage] = useState("");
    const [phoneNumberMessage, setPhoneNumberMessage] = useState("");


    const [notOverlap, setNotOverlap] = useState(false);
    const [validPassword, setvalidPassword] = useState(false);
    const [validPasswordcheck, setvalidPasswordcheck] = useState(false);
    const [validName, setvalidName] = useState(false);
    const [validNickname, setvalidNickname] = useState(false);
    const [validEmail, setvalidEmail] = useState(false);
    const [validPhoneNumber, setvalidPhoneNumber] = useState(false);

    let testId = new RegExp(/^[a-z0-9]+[a-z0-9]{4,19}$/g);
    let testName = new RegExp( /^[가-힣a-zA-Z]+$/);
    let testEmail = new RegExp(/[a-z0-9]+@[a-z]+\.[a-z]{2,3}/);
    let testPassword = new RegExp(/^[A-Za-z0-9]{8,20}$/);
    let testPhoneNumber = new RegExp(/^[0-9\b -]{0,13}$/);


    // 정규식 js로 따로 만들어서 관리 할 것
    const btnOnClick = () =>{

        if(notOverlap&&validPassword&&validPasswordcheck&&validName&&validNickname&&validEmail&&validPhoneNumber === true){
            const joinRequestBody = {
                userId:memberId, 
                password:password,
                name: name,
                nickname:nickname,
                email:email,
                phoneNumber:phoneNumber
            };

            const loginRequestBody = {
                userId:memberId, 
                password:password
            };
            
            PostAxios('/member/join', joinRequestBody, ()=>{
                                        axios.get('https://geolocation-db.com/json/')
                                        .then((res) => {
                                            console.log(res);
                                            loginRequestBody.accessIp = res.data["IPv4"];
                                            loginRequestBody.latitude = res.data["latitude"];
                                            loginRequestBody.longitude = res.data["longitude"]
                            
                                            PostAxios('/member/login', loginRequestBody, (data)=>{
                                                console.log(data);
                                                sessionStorage.setItem("token", data.data);
                                                sessionStorage.setItem("userId", memberId);
                                            }).then((res)=>{
                                                console.log(res);
                                                navigate('/join/profile',{state:{userId:memberId}});
                                            })
                                        })
                                    })
        
        }else{
            alert("회원 정보를 다시 정확하게 기재해주세요")
        }
    }

    const onBlurMemberId = () =>{

        if(!testId.test(memberId)){
            setMemberIdMessage("아이디는 5~18자의 영문 소문자 및 숫자만 사용 가능합니다.")
            setNotOverlap(false)
        }
        else if(memberId.length > 4) {
                GetAxios('/member/overlap',
                {params: {userId:memberId}},
                (data)=> {
                    console.log(data)
                    if(!data.data){
                            setMemberIdMessage("멋진 아이디네요!")
                            setNotOverlap(true)
                }else{
                    setMemberIdMessage("중복된 아이디입니다.")
                    setNotOverlap(false)
                }
        })
        }
        else{
            setMemberIdMessage("")
        }
    }

    const onBlurPassword = () =>{
        if(!testPassword.test(password)) { 
            setPasswordMessage("8~20자 영문 대 소문자, 숫자를 사용하세요.");
            setvalidPassword(false);
        }else{
            setPasswordMessage("")
            setvalidPassword(true);
        }
    }

    const onBlurPasswordCheck = () => {
        if(password !== passwordcheck){
            setPasswordCheckMessage("비밀번호가 일치하지 않습니다.")
            setvalidPasswordcheck(false)
        }
        else{
            setPasswordCheckMessage("")
            setvalidPasswordcheck(true)
        }
    }

    const onBlurName = () => {
        if(!testName.test(name) || name.length > 10){
            setNameMessage("이름은 10자 이내의 영 대소문자 및 한글만 입력 가능합니다.");
            setvalidName(false);
        }else{
            setNameMessage("");
            setvalidName(true);
        }
    }

    const onBlurNickname = () => {
        if(nickname.length < 4){
            setNicknameMessage("4자리 이상의 닉네임을 사용하세요.")
            setvalidNickname(false)
        }else{
            setNicknameMessage(`${nickname}님 멋진 닉네임이네요`)
            setvalidNickname(true)
        }
    }

    const onBlurEmail = () => {
        if(!testEmail.test(email)){
            setEmailMessage("올바른 이메일 형식이 아닙니다.")
            setvalidEmail(false)
        }
        else{
            setEmailMessage("")
            setvalidEmail(true)
        }
    }

    const onBlurPhoneNumber = () => {
        if(!testPhoneNumber.test(phoneNumber) || phoneNumber.length < 11){
            setPhoneNumberMessage("올바른 휴대폰 번호를 입력해주세요.")
            setvalidPhoneNumber(false)
        }
        else{
            setPhoneNumberMessage("")
            setvalidPhoneNumber(true)
        }
    }

    return (
        <div className="member_wrapper">
        <div className='member_form'>
        <img className='login_logo' src={process.env.PUBLIC_URL + `img/login_logo.png`} onClick={()=> {navigate('/')}} alt='logo'/>
        <br/>
        <br/>
            <input className="member_text" type='text' name="memberId" value={memberId} onBlur={onBlurMemberId}
            onChange={onChange} placeholder="아이디를 입력해주세요."/>
        <br/>
            <span className="message">{memberIdMessage}</span>    
        <br/>
            <input className="member_text" type='password' name="password" value={password} onBlur={onBlurPassword} onChange={onChange} 
                 placeholder="8~20자리의 비밀번호를 입력해주세요."/>
        <br/>
            <span className="message">{passwordMessage}</span>    
        <br/>
            <input className="member_text" type='password' name="passwordcheck" value={passwordcheck} onBlur={onBlurPasswordCheck} 
                onChange={onChange} placeholder="비밀번호를 다시 입력해주세요."/>
        <br/>
            <span className="message">{passwordCheckMessage}</span>  
        <br/>
        <input className="member_text" type='text' name="name" value={name} onBlur={onBlurName} 
                onChange={onChange} placeholder="이름을 입력해주세요."/>
        <br/>
            <span className="message">{nameMessage}</span>  
        <br/>
            <input className="member_text" type='text' name="nickname" value={nickname} onBlur={onBlurNickname}
             onChange={onChange}  placeholder="닉네임을 입력해주세요."/>
        <br/>
            <span className="message">{nicknameMessage}</span>        
        <br/>
            <input className="member_text" type='text' name="email" value={email} onBlur={onBlurEmail}
            onChange={onChange}  placeholder="이메일을 입력해주세요."/>
       <br/>
            <span className="message">{emailMessage}</span>         
        <br/>
            <input className="member_text" type='text' name="phoneNumber" value={phoneNumber} onBlur={onBlurPhoneNumber}
            onChange={onChange} maxLength={11} placeholder="전화번호를 입력해주세요."/>
       <br/>
            <span className="message">{phoneNumberMessage}</span>        
      
        <div className='find_btn_wrapper'>
                <MyButton text={'가입하기'} onClick={btnOnClick}/>
            </div>
        </div>
        </div>
    );
}
export default Join;