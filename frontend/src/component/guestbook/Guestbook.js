import MyButton from "../button/MyButton";
import Profile from "../home/homeContents/Profile";
import Friends from "../home/homeContents/Friends";
import { useNavigate } from "react-router-dom";
import UploadedFriends from "../home/homeContents/UploadedFriends";


const Guestbook = () => {
    const navigate = useNavigate();

    return (
    <div className="all_wrapper">
        <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
        <div className="main_wrapper">
            <div className="menu_wrapper">
                <MyButton text={'방명록'} type={'nothing'} onClick={()=>{navigate('/home/feed')}}/>
            </div>
            <div className="home_wrapper">
            <br/>
        <br/>
        //########### 방명록 컴포넌트가 들어갈 자리 ###########//
            </div>
            </div>
        <div className="sub_wrapper">
            <Profile/>
            <Friends/>
        </div>
    </div>
    );

}
export default Guestbook;