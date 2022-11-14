import { useState, useEffect } from "react";
import getAxiosFn from "../../utils/axios/getAxiosFn";
import MyButton from "../button/MyButton";

const MyInfo = () => {
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");

    let userId = sessionStorage.getItem("userId");
    
    useEffect(() => getAxiosFn('/member/' + userId, {params:{}})
        .then((res) => {
            setNickname(res.nickname);
            setEmail(res.email);
            setPhoneNumber(res.phoneNumber);
        })
    , []);

    return (
        <div className="user_info_wrpper">
            <div className="member_info">
            <span className="userid_message">회원 정보</span>
        <br/>
        <br/>
            <img className="profile"src= {process.env.PUBLIC_URL + `../.././img/test5.jpeg`}/>
        <br/>
            <input className="member_text" type='text' value={sessionStorage.getItem("userId")} readOnly/>
        <br/>
            <input className="member_text" name = "nickname" type="text" value={nickname} onChange={e => {
                console.log(e.target.value);
                setNickname(e.target.value);
            }}/>
        <br/>
            <input className="member_text" type='text' value={email} onChange={()=>{}}/>
        <br/>
            <input className="member_text" type='text'  value={phoneNumber} onChange={()=>{}} />
        <br/>
        <br/>
        <br/>
            <MyButton text={' 저장 '}/>
            <MyButton text={' 비밀번호 변경 '} type={'positive'}/>
            </div>
        </div>
    );
}

export default MyInfo;