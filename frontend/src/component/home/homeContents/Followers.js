import { useNavigate } from "react-router-dom";
import MyButton from "../../button/MyButton";
import Profile from "./Profile";
import Friends from "./Friends";
import { useEffect, useState } from "react";
import GetAxios from "../../../utils/axios/GetAxios";
import DeleteAxios from "../../../utils/axios/DeleteAxios";


const Followers = () => {
    const navigate = useNavigate();
    const [followerBtn, setFollowerBtn] = useState("nothing");
    const [followingBtn, setFollowingBtn] = useState("");

    const [followerList, setFollowerList] = useState([]);
    const [followingList, setFollowingList] = useState([]);


    useEffect(()=>{
        GetAxios('/following/my-followers',{params:{}},(res)=>{setFollowerList(res.data)})
    },[])

    const onToggleActive1 = () => {
        setFollowerBtn("nothing")
        setFollowingBtn("")

        GetAxios('/following/my-followers',{params:{}},(res)=>{setFollowerList(res.data)})
    }

    const onToggleActive2 = () => {
        setFollowingBtn("nothing")
        setFollowerBtn("")

        GetAxios('/following/my-followings',{params:{}},(res)=>{setFollowingList(res.data)})
    }

    const unfollow = (e) => {
        DeleteAxios('/following/'+e,{params:{}},()=>{window.location.reload()})
    }


    return (
        <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
   <div className="main_wrapper">
       <div className="menu_wrapper">
           <MyButton text={'팔로워'} type={followerBtn} onClick={onToggleActive1}/>
           <MyButton text={'팔로잉'} type={followingBtn} onClick={onToggleActive2} />
       </div>
   <div className="home_wrapper">
   <br/>
    <br/>
    <br/>
    <div>
        {followerBtn === "nothing"&& followerList.length > 0 ? followerList && followerList.map(follower => (
            <div className="social_list" key={follower.memberId}>
                <img className="social_profile" src={follower.profileUrl} onClick={()=>navigate('/drawer/'+follower.memberId)} alt="profile"/>
                <div style={{}}>{follower.nickname}({follower.memberId})</div>
                <MyButton type={'negative'} text={'삭제'}/>
            </div>
        )) : followerBtn === "nothing" ? <div className="social_list"> 
            <img className="default_page" src={ process.env.PUBLIC_URL + `../.././img/no_followers.png`} alt="profile"/> </div>
            : <></> }

        {followingBtn === "nothing"&& followingList.length > 0 ? followingList && followingList.map(following => (
            <div className="social_list" key={following.memberId}>
                <img className="social_profile" src={following.profileUrl} onClick={()=>navigate('/drawer/'+following.memberId)} alt="profile"/>
                <br/>
                <span className="social_msg">{following.nickname}({following.memberId})</span>
                <MyButton type={'positive'} text={'팔로잉'} onClick={()=>unfollow(following.memberId)}/>
            </div>
        )) : followingBtn === "nothing" ? <div className="social_list"> 
                <img className="default_page" src={ process.env.PUBLIC_URL + `../.././img/no_followings.png`}/> 
                </div> : <></> }
    </div>
   </div>
   </div>
   <div className="sub_wrapper">
       <Profile/>
       <Friends/>
   </div>
   </div>
    );
}

export default Followers;