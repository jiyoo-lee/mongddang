import {useNavigate} from "react-router-dom";


const Profile = () => {
    
    const navigate = useNavigate();

    return (
        <div className="profile_wrapper">
            <div className="img_wrapper">
            <br/>
                    <img className='profile_img' src='../.././img/test5.jpeg'/>
            </div>
        <br/>
            <div className="social_wrapper">
                <div className="social"> 지유 [jeeyu] <br/><br/>
                팔로우  <span className="people">98981</span> | 팔로잉 <span className="people"> 907</span> </div>
            </div>
        <br/>
            <div className="top_menu_wrapper">
                <ul className ="top_menu_ul">
                    <li className="top_menu" onClick={()=>{navigate('/home/feed')}}> <img className='top_menu_logo' src='../.././img/home_logo.png' alt="logo"/>홈 </li>
                    <li className="top_menu" onClick={()=>{navigate('/drawer')}}> <img className='top_menu_logo' src='../.././img/drawer_logo.svg' alt="logo"/> 나의 서랍</li>
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/guestbook_logo.svg' alt="logo"/>  방명록 </li>
                    <li className="top_menu"> <img className='top_menu_logo' src='../.././img/ac_logo.svg' alt="logo"/>  예술의 몽땅 </li>
                </ul>
            </div>
        </div>
    );
}

export default Profile;