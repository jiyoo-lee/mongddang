import MyButton from "../../button/MyButton";
import Friends from "../homeContents/Friends";
import { useNavigate, useLocation } from "react-router-dom";
import UploadedFriends from "../homeContents/UploadedFriends";
import Profile from "../homeContents/Profile";
import MyDrops from "./MyDrops";


const NewDrops = () => {
    const navigate = useNavigate();

    const location = useLocation();
    const options = location.state.options;
    console.log(options);
        return (
            <div className="all_wrapper">
            <img className='login_logo' src={process.env.PUBLIC_URL + `../.././img/login_logo.png`} onClick={()=> {navigate('/home/feed')}} alt='login_logo'/>
            <div className="main_wrapper">
                <div className="menu_wrapper">
                    <MyButton text={'피드'}  onClick={()=>{navigate('/home/feed')}}/>
                    <MyButton text={'베스트'} onClick={()=>{navigate('/home/best')}}/>
                    <MyButton text={'지금 그림'}/>
                    <MyButton text={'업로드'} type={'nothing'} 
                              onClick={()=>navigate('/home/upload')}/>
                </div>
                <div className="home_wrapper">
                <br/>
                    <UploadedFriends/>
                <br/>
                    <MyDrops options={options}/>
                </div>
                </div>
            <div className="sub_wrapper">
                <Profile/>
                <Friends/>
            </div>
        </div>
        );
}

export default NewDrops;