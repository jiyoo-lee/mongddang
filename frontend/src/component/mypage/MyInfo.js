import { useState, useEffect} from "react";
import { useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import PutAxios from "../../utils/axios/PutAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";
import MyButton from "../button/MyButton";

const MyInfo = () => {

    const navigate = useNavigate();

    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [profile, setProfile] = useState("");
  

    let userId = sessionStorage.getItem("userId");

    useEffect( (e) => {
        async function fetchData(){
            await GetAxios('/member/'+userId,{params:{}},(res)=>{
                setNickname(res.data.nickname)
                setEmail(res.data.email)
                setPhoneNumber(res.data.phoneNumber)
                setProfile(res.data.profilePicture)
            })
        }
            fetchData();
    },[]);

    const requestBody = {
        userId: userId,
        nickname : nickname,
        email : email,
        phoneNumber : phoneNumber,
    }
    const onEdit = () => {
        PutAxios('/member/',requestBody,(res)=>{console.log(res)})
    }

    const onDelete = () => {
        DeleteAxios('/member/'+userId+'/profile-picture',{params:{}},window.location.reload())
    }

    return (
        <div className="user_info_wrpper">
            <div className="member_info">
            <span className="userid_message">회원 정보</span>
        <br/>
        <br/>
            <img className="profile" src={profile === null? process.env.PUBLIC_URL + `../.././img/present_logo.png` : profile}/>
            <img className="info_icon" src={process.env.PUBLIC_URL + `../.././img/edit_icon.svg`} onClick={()=>navigate('/profile')}/>
            <img className="info_icon" src={process.env.PUBLIC_URL + `../.././img/delete_icon.svg`} onClick={onDelete}/>
        <br/>
            <input className="member_text" type='text' value={sessionStorage.getItem("userId")} readOnly/>
        <br/>
            <input className="member_text" name = "nickname" type="text" value={nickname} onChange={(e) => {
                setNickname(e.target.value)}}/>
        <br/>
            <input className="member_text" type='text' value={email} onChange={ (e) => {
                setEmail(e.target.value)}}/>
        <br/>
            <input className="member_text" type='text'  value={phoneNumber} onChange={(e) => {
                setPhoneNumber(e.target.value)}} />
        <br/>
        <br/>
        <br/>
            <MyButton text={' 저장 '} onClick={onEdit}/>
            <MyButton text={' 비밀번호 변경 '} type={'positive'}/>
            </div>
        </div>
    );
}

export default MyInfo;