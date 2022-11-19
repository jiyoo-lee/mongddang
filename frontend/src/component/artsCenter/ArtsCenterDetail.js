import { useEffect, useState } from "react";
import { useParams, useLocation, useNavigate } from "react-router-dom";
import LogoHeader from "../LogoHeader";
import GetAxios from "../../utils/axios/GetAxios";
import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import Heart from "../like/Heart";

const ArtsCenterDetail = () => {
    const location = useLocation();
    const contest = location.state.contest;
    const { contestId } = useParams();
    const [paintings, setPaintings] = useState([]);
    const navigate = useNavigate();
    
    useEffect(()=>{
        GetAxios('/arts-center/' + contestId + '/painting', {params:{}}, res => {setPaintings(res.data)
        console.log(res.data)});
    }, []);

    const canRegist = ()=>{
        const now = new Date().getTime();
        const start = new Date(contest.startDay.substr(0, 10)).getTime();
        const end = new Date(contest.endDay.substr(0, 10)).getTime();

        return start <= now && end >= now;
    }

    const onUpload = () => {
        navigate('/arts-center/'+contestId+'/upload')
    }

    const onSearchMyPainting = () => {
        navigate('/arts-center/'+contestId+'/upload/'+sessionStorage.getItem("userId"))
    }

    return (
            <div className="all_wrapper">
                <LogoHeader/>
                <div className="main_wrapper">
                    <div className="menu_wrapper">
                        <MyButton text={'진행 중'} type={'nothing'} onClick={()=>{navigate('/home/feed')}}/>
                        <MyButton text={'지난 공모전'} onClick={()=>{navigate('/arts-center/last')}}/>
                    </div>
                    <div className="home_wrapper">
                <br/>
                <br/>
                <div className="button_wrapper">    
                    <MyButton text={'도전하기'} onClick={onUpload}/>
                    <MyButton text={'내 그림 찾기'} type={'positive'} onClick={onSearchMyPainting}/>
                </div>
                <div className="my_feed_wrapper">
                <br/>
                <br/>
                    {paintings.map(painting=>(
                    <div key={painting.paintingId}>
                         <div className="items">
                            <img className="items_img" src={painting.contestPaintingUrl} alt={painting.title} />
                        </div>
                    <div className="items_desc">
                        <img className="items_profile" src={painting.profileUrl} alt={painting.memberId} /> 
                        <span className="user_info">{painting.nickname}({painting.memberId})</span>
                    <br/>
                        <p className="items_title">{painting.title}</p>
                        <p className="items_content">{painting.description}</p>
                        <span className="user_like">
                            <Heart id={painting.paintingId} like={painting.isLike} count={painting.mongddangCount} type="contest"/>
                            <span className="user_like"><img className="icon" src="../.././img/comment.png" alt="comment" 
                                                            onClick={()=>navigate('/arts-center/'+painting.paintingId+'/comments')}/>
                                {painting.comment}</span>
                        </span>
                    <br />
                    </div>
                    </div>
                ))}
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
export default ArtsCenterDetail;