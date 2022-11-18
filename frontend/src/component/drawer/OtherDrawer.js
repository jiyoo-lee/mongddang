import { useEffect, useState } from "react";
import { useParams,useNavigate } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import MyButton from "../button/MyButton";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";

const OtherDrawer = () => {
    const [state, setState] = useState([]);
    const [info, setInfo] = useState([]);
    const { userId } = useParams();
    const navigate = useNavigate();

    const goToPage = (dropsId) => {
        navigate('/drawer/paintings',{state:{dropsId:dropsId}})
    }

    useEffect(()=>{
        console.log(userId);

        GetAxios('/drawer/' + userId, {param:{}}, (res)=>{
            console.log(res.data);
            setState(res.data);
            setInfo(res.data.drawerUserInfo);
        });
    }, []);

    const follow = () =>{
        PostAxios('/following/'+userId,{},(res)=>{window.location.reload()})
    }

    const unfollow = () => {
        DeleteAxios('/following/'+userId,{},(res)=>{window.location.reload()})
    }

    return (
    <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
        <div className="main_wrapper">
        <div className="menu_wrapper">
            <MyButton text={'서랍장'} type={'nothing'} onClick={()=>{navigate('/drawer/'+userId)}}/>
            <MyButton text={'몽땅'} onClick={()=>{navigate('/drawer/like/'+userId)}}/>
            <MyButton text={'방명록'} onClick={()=>{navigate('/guestbook/'+userId)}}/>
        </div>
        <div className="home_wrapper">
            <div className="drawer_wrapper">
            <span className="userid_message"> 안녕하세요, 이곳은 {userId}님의 서랍장입니다. </span>
            <br/>
             
                <img className="friendsRecommend" src={info.profileUrl}/> 
                <br/>
                <span className="drawer_msg"> 팔로워 </span>  <span className="userid_message"> {info.followerCount} </span>
                <span className="drawer_msg"> 팔로워 </span>  <span className="userid_message"> {info.followingCount} </span>
                {info.isFollow === true? <MyButton text={'팔로우함'} type={'positive'} onClick={unfollow}/> : <MyButton text={'팔로우하기'} onClick={follow}/>}
            <br/>
            <span className="drawer_msg"> 드랍 개수 : </span> <span className="userid_message">{state.paintingCount} </span>
            <span className="drawer_msg"> 그림 개수 : </span>
            {state.paintingCountGroupingGenre && state.paintingCountGroupingGenre.map(genre => (
                <span key={genre.genreName}>{genre.genreName} : <span className="userid_message">{genre.count} </span></span>
            ))}
        
        <br/>
        <br/>
        <br/>
            <div>
                {state.drops && state.drops.map(drops=>(
                    <div key={drops.dropsId} className="drops_list">
                        <img className="items_img" src={drops.lastPaintingUrl} onClick={()=>goToPage(drops.dropsId)}/> <br/>
                        <span className="genre">{drops.dropsGenre}</span> <br/><br/>
                        <span className="drawer_msg">{drops.dropsName}</span> <br/><br/>
                        <img className="icon" src="../.././img/like.png" alt="like"/> {drops.mongddangCount}
                    </div>
                ))}
            </div>
        </div>
        </div>
        </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    )
}

export default OtherDrawer;