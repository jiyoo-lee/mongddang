import { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
import GetAxios from "../../../utils/axios/GetAxios";


const Profile = () => {
    
    const navigate = useNavigate();

    const [nickname, setNickname] = useState("");
    const [profile, setProfile] = useState("");
    const [following ,setFollowing] = useState("");
    const [followers ,setFollowers] = useState("");
    let userId = sessionStorage.getItem("userId");

    useEffect( (e) => {
        async function fetchData(){
            await GetAxios('/member/'+userId,{params:{}},(res)=>{
                setNickname(res.data.nickname)
                setProfile(res.data.profilePicture)
            }).then (GetAxios('/following/count/'+userId,{params:{}},(res)=>{
                setFollowers(res.data.followers)
                setFollowing(res.data.followings)
            }))
        }
            fetchData();
    },[]);

    return (
        <div className="profile_wrapper">
            <div className="img_wrapper">
            <br/>
                    <img className='profile_img' src={profile}/>
            </div>
        <br/>
            <div className="social_wrapper">
                <div className="social"> {nickname} ({userId}) <br/><br/>
                팔로워  <span className="people" onClick={()=>navigate('/drawer/buddy/'+userId)}>{followers}</span> 
                 
                팔로잉 <span className="people" onClick={()=>navigate('/drawer/buddy/'+userId)}>{following}</span> </div>
            </div>
        <br/>
        <br/>
            <div className="top_menu_wrapper">
                <ul className ="top_menu_ul">
                    <li className="top_menu" onClick={()=>{navigate('/home/feed')}}> <img className='top_menu_logo' src='../.././img/home_logo.png' alt="logo"/>홈 </li>
                    <li className="top_menu" onClick={()=>{navigate('/drawer')}}> <img className='top_menu_logo' src='../.././img/drawer_logo.svg' alt="logo"/> 나의 서랍</li>
                    <li className="top_menu" onClick={()=>{navigate('/guestbook/' + userId)}}> <img className='top_menu_logo' src='../.././img/guestbook_logo.svg' alt="logo"/>  방명록 </li>
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/ac_logo.svg' alt="logo"/>  예술의 몽땅 </li>
                </ul>
            </div>
        </div>
    );
}

export default Profile;